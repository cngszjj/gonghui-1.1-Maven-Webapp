package com.jojoz.gh.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jojoz.gh.dto.GHRsult;
import com.jojoz.gh.dto.UnionRsult;
import com.jojoz.gh.dto.UnionVO1;
import com.jojoz.gh.entity.Union;
import com.jojoz.gh.entity.User;
import com.jojoz.gh.service.UnionService;
import com.jojoz.gh.service.UserService;
import com.jojoz.gh.util.ExcelUtil;

@Controller
@RequestMapping("/union")
public class UnionController {

	@Autowired
	private UnionService unionService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<Union> savaUnion(HttpServletRequest request,
			@RequestParam("legalPhoto") MultipartFile file,
			@CookieValue(value = "user", required = false) String userStr,
			Union union) throws IllegalStateException, IOException {

		User user = userService.userValidate(userStr);
		if (null == user) {
			return new GHRsult<Union>(false, "need login");
		}
		String filePath;
		if (file!=null) {// 判断上传的文件是否为空
            String path=null;// 文件路径
            String type=null;// 文件类型
            String fileName=file.getOriginalFilename();// 文件原名称
            System.out.println("上传的文件原名称:"+fileName);
            // 判断文件类型
            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
            if (type!=null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                    // 项目在容器中实际发布运行的根路径
                    String realPath=request.getSession().getServletContext().getRealPath("/");
                    // 自定义的文件名称
                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
                    filePath = trueFileName;
                    // 设置存放图片文件的路径
                    path=realPath+/*System.getProperty("file.separator")+*/"/upload/"+trueFileName;
//                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
//                    System.out.println("文件成功上传到指定目录下");
                }else {
//                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                	return new GHRsult<Union>(false, "image error");
                }
            }else {
//                System.out.println("文件类型为空");
            	return new GHRsult<Union>(false, "image error");
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return new GHRsult<Union>(false, "image error");
        }

		if (union != null) {

			int result = 0;
			union.setUser_id(user.getId());
			if(user.getState()>1){
				result = unionService.savaUnion(union);
			}else{
				union.setPhotoUrl(filePath);
				Union u = unionService.getUnionByUser(user);
				if (u != null) {
					return new GHRsult<Union>(false, "union exist");
				}
				result = unionService.savaUnion(union);
			}
			
			
			if (1 == result) {
				return new GHRsult<Union>(true, "1");
			}

		}

		return new GHRsult<Union>(false, "save error");

	}

	/**
	 * 获取union关键字段列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public UnionRsult<Object[]> query(
			@CookieValue(value = "user", required = false) String userStr,
			String words, Integer state,String pd_words,String dstart,String dend, Integer page, Integer rows) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			return null;
		}
		// 没有权限
		if (user.getState() < 2) {
			return null;
		}
		Object[] uvo = unionService.query(words, state, rows, page, user ,pd_words,dstart,dend)
				.toArray();
		return new UnionRsult<Object[]>(uvo.length, uvo);

	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public GHRsult<Union> getUnionByUser(
			@CookieValue(value = "user", required = false) String userStr) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			return new GHRsult<Union>(false, "not login");
		}
		Union union = unionService.getUnionByUser(user);
		if (null == union) {
			return new GHRsult<Union>(false, "no union");
		}
		return new GHRsult<Union>(true, union);

	}

	@RequestMapping(value = "/union", method = RequestMethod.GET)
	@ResponseBody
	public GHRsult<Union> getUnionByUserId(
			@CookieValue(value = "user", required = false) String userStr,
			String id) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			return new GHRsult<Union>(false, "not login");
		}
		Union union = unionService.getUnionById(id);
		if (null == union) {
			return new GHRsult<Union>(false, "no union");
		}
		return new GHRsult<Union>(true, union);

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public GHRsult<Union> updateUnion(
			@CookieValue(value = "user", required = false) String userStr,
			Union union) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			return new GHRsult<Union>(false, "not login");
		}
		int result = unionService.updateUnion(union);
		if (result == 0) {
			return new GHRsult<Union>(false, "update fail");
		}
		return new GHRsult<Union>(true, union);

	}
	
	@RequestMapping(value = "/exportUnion", method = RequestMethod.GET)
	public String exportUnion(HttpServletRequest request,HttpServletResponse response,
			@CookieValue(value = "user", required = false) String userStr,
			String words, Integer state,String pd_words,String dstart,
			String dend, Integer page, Integer rows
			) throws IOException{
		User user = userService.userValidate(userStr);
		if (null == user) {
			return null;
		}
		// 没有权限
		if (user.getState() < 2) {
			return null;
		}
		 String fileName="union";
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		 fileName = fileName+"("+sdf.format(new Date())+")";
	        //填充projects数据
		 	//按条件全部查出
	        List<UnionVO1> projects=unionService.query(words, state, 100000, 1, user ,pd_words,dstart,dend);
	        List<Map<String,Object>> list=createExcelRecord(projects);
	        String columnNames[]={"序号","机构名称","住所地址","法定代表人","证件号码",
	        		"手机号码","第一届委员会成立日期","本届委员会成立日期","发证日期",
	        		"职工人数","统一社会信用代码","企业性质","所属行业"};//列名
	        String keys[] = {"num","unionName","registerAddress","legal","legalCredCode",
	        		"legalPhone","firstSetupTime","thisSetupTime","issueDate",
	        		"workersCount","unionCardCode","unitNature","unitType"};//map中的key
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        try {
	            ExcelUtil.createWorkBook(list,keys,columnNames).write(os);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
	        try {
	            bis = new BufferedInputStream(is);
	            bos = new BufferedOutputStream(out);
	            byte[] buff = new byte[2048];
	            int bytesRead;
	            // Simple read/write loop.
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	                bos.write(buff, 0, bytesRead);
	            }
	        } catch (final IOException e) {
	            throw e;
	        } finally {
	            if (bis != null)
	                bis.close();
	            if (bos != null)
	                bos.close();
	        }
	        return null;

	}
	
//	private List<UnionVO1> createData() {
//        // TODO Auto-generated method stub
//        //自己实现
//        return unionService.getUnionForExport();
//    }
    private List<Map<String, Object>> createExcelRecord(List<UnionVO1> unions) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        UnionVO1 project=null;
//        {"num","unionName","registerAddress","legal","legalCredCode",
//    		"legalPhone","firstSetupTime","thisSetupTime","issueDate",
//    		"workersCount","unionCardCode","unitNature","unitType"}
        for (int j = 0; j < unions.size(); j++) {
            project=unions.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("num", j+1);
            mapValue.put("unionName", project.getUnionName());
            mapValue.put("registerAddress", project.getRegisterAddress());
            mapValue.put("legal", project.getLegal());
            mapValue.put("legalCredCode", project.getLegalCredCode());
            mapValue.put("legalPhone", project.getLegalPhone());
            mapValue.put("firstSetupTime", project.getFirstSetupTime());
            mapValue.put("thisSetupTime", project.getThisSetupTime());
            mapValue.put("issueDate", project.getIssueDate());
            mapValue.put("workersCount", project.getWorkersCount());
            mapValue.put("unionCardCode", project.getUnionCardCode());
            mapValue.put("unitNature", project.getUnitNature());
            mapValue.put("unitType", project.getUnitType());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
