package com.jojoz.gh.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
//	@RequestMapping(value = "/fileUploadTest", method = RequestMethod.POST)
//	public String fileUploadTest(@RequestParam("file") MultipartFile file,
//			HttpServletRequest request){
//		
//		// 判断文件是否为空  
//        if (!file.isEmpty()) {  
//            try {  
//                // 文件保存路径  
//                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/"  
//                        + file.getOriginalFilename();  
//                // 转存文件  
//                file.transferTo(new File(filePath));  
//            } catch (Exception e) {  
//                e.printStackTrace();  
//            }  
//        }  
//        // 重定向  
//        return "succ";  
//		
//	}
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
                    System.out.println("存放图片文件的路径:"+path);
                    // 转存文件到指定的路径
                    file.transferTo(new File(path));
                    System.out.println("文件成功上传到指定目录下");
                }else {
                    System.out.println("不是我们想要的文件类型,请按要求重新上传");
                    return null;
                }
            }else {
                System.out.println("文件类型为空");
                return null;
            }
        }else {
            System.out.println("没有找到相对应的文件");
            return null;
        }

		if (union != null) {
			union.setUser_id(user.getId());
			union.setPhotoUrl(filePath);
			Union u = unionService.getUnionByUser(user);
			int result = 0;
			if (u != null) {
				return new GHRsult<Union>(false, "union exist");
			}
			result = unionService.savaUnion(union);
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
			String words, Integer state, Integer page, Integer rows) {
		User user = userService.userValidate(userStr);
		if (null == user) {
			return null;
		}
		// 没有权限
		if (user.getState() < 2) {
			return null;
		}
		Object[] uvo = unionService.query(words, state, rows, page, user)
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
	public String exportUnion(HttpServletRequest request,HttpServletResponse response) throws IOException{
		
		 String fileName="excel文件";
	        //填充projects数据
	        List<Union> projects=createData();
	        List<Map<String,Object>> list=createExcelRecord(projects);
	        String columnNames[]={"ID","项目名","销售人","负责人","所用技术","备注"};//列名
	        String keys[]    =     {"id","name","saler","principal","technology","remarks"};//map中的key
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
	
	private List<Union> createData() {
        // TODO Auto-generated method stub
        //自己实现
        return unionService.query(null, check, pageSize, pageNum, user);
    }
    private List<Map<String, Object>> createExcelRecord(List<Union> projects) {
        List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sheetName", "sheet1");
        listmap.add(map);
        Project project=null;
        for (int j = 0; j < projects.size(); j++) {
            project=projects.get(j);
            Map<String, Object> mapValue = new HashMap<String, Object>();
            mapValue.put("id", project.getId());
            mapValue.put("name", project.getName());
            mapValue.put("technology", project.getTechnology());
            mapValue.put("remarks", project.getRemarks());
            listmap.add(mapValue);
        }
        return listmap;
    }

}
