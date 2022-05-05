package com.testservlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bean.Info;
import com.bean.Type;
import com.bean.User;
import com.dao.TypeDao;
import com.dao.impl.InfoDaoImpl;
import com.dao.impl.TypeDaoImpl;
import com.service.impl.ServiceInfoImpl;
import com.service.impl.ServiceUserImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class ServletTest extends HttpServlet {
    String operate =null;
    @Override
    public void init(ServletConfig config) throws ServletException {

        operate = config.getInitParameter("operate");
        System.out.println(">>>>>>><<<<<<<<<"+operate);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    if ("dele".equals(operate)){
    this.dele(request,response);
    }else if("edit".equals(operate)){
        this.edit(request,response);
        }else if("myjsp".equals(operate)){
        this.page(request,response);
    }else if("type".equals(operate)){
        this.type(request,response);}
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("add".equals(operate)){
            this.add(request,response);


        }
    }
    protected void type(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        JSONArray array=new JSONArray();
        TypeDao typedao=new TypeDaoImpl();
        List<Type> typelist=typedao.queryAll();
        for (int i = 0; i <typelist.size() ; i++) {
            JSONObject typeObj =new JSONObject();
            typeObj.put("newstype", typelist.get(i).getTypeName());
            System.out.println(typeObj);
            array.add(typeObj);

        }
        response.getWriter().write(array.toString());
    }



    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int  page1 =Integer.parseInt(request.getParameter("page1")==null?"1":request.getParameter("page1"));
        InfoDaoImpl infodaoimpl=new InfoDaoImpl();
        String typestr=request.getParameter("typestr");
System.out.println("typestr----------"+typestr);
        String keywords=request.getParameter("keywords");
System.out.println("keywords--------"+keywords);
        List<Info> list = infodaoimpl.queryBypage(page1,3,typestr,keywords);



        request.setAttribute("list",list);
        request.setAttribute("page1",page1);
        InfoDaoImpl infodaoimpl1=new InfoDaoImpl();

        int size=infodaoimpl.count();
        request.setAttribute("size",size);


       /* request.getRequestDispatcher("MyJsp.jsp").forward(request,response);*/
        StringBuilder builder=new StringBuilder();

        JSONArray array=new JSONArray();


        response.setCharacterEncoding("UTF-8");
        for (int i = 0; i < list.size(); i++) {

            JSONObject jsonObj =new JSONObject();
            jsonObj.put("newsId",list.get(i).getNewsId());
            jsonObj.put("newsTitle",list.get(i).getNewsTitle());
            jsonObj.put("newsType",list.get(i).getNewsType().getTypeName());
            jsonObj.put("sendUser",list.get(i).getSendUser().getRealName());
            jsonObj.put("sendTime",list.get(i).getSendTime().toString());
            array.add(jsonObj);
        };

        JSONObject jsonObj =new JSONObject();
        jsonObj.put("page1",page1);
        jsonObj.put("size",size);
        array.add(jsonObj);







        response.getWriter().write(array.toString());

    }



    ServiceInfoImpl info =new ServiceInfoImpl();
    Info obj = new Info();

    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletFileUpload uplod= new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> itemlist = null;

        try {
            itemlist = uplod.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        boolean a=false;

        for (int i = 0; i < itemlist.size(); i++){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if(itemlist.get(i).isFormField()){
                if("newsTitle".equals(itemlist.get(i).getFieldName())){
                    obj.setNewsTitle(itemlist.get(i).getString("UTF-8"));

                }
                User user = new User();
                user.setUserId(((User)request.getSession().getAttribute("login")).getUserId());
                obj.setSendUser(user);

                if("newsType".equals(itemlist.get(i).getFieldName())){
                    obj.setNewsType(new Type(new Integer(itemlist.get(i).getString("UTF-8"))));}


                if("newsContent".equals(itemlist.get(i).getFieldName())){
                    obj.setNewsContent(itemlist.get(i).getString("UTF-8"));}
                if("sendTime".equals(itemlist.get(i).getFieldName())){
                    try {
                        obj.setSendTime(sdf.parse(itemlist.get(i).getString("UTF-8")));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }else{
/*  if(itemlist.get(i).getSize()>100*1024 || itemlist.get(i).getSize()<=0 ){
		System.out.print("文件大小不符合");
		a=false;
		continue;
		}   */
                String file = itemlist.get(i).getName();
                if(file==null){
                    continue;
                }
                String size =	file.substring(file.lastIndexOf("."));
                file = UUID.randomUUID()+size;

                String realpath=request.getServletContext().getRealPath("/");
                File filee = new File (realpath+"upload");
                if(!filee.exists()){
                    filee.mkdirs();
                }
                try {
                    itemlist.get(i).write(new File(filee,file));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                obj.setNewsImg(realpath+"upload\\"+file);
                System.out.print(obj.getNewsImg());
                /*if(a==false){
                    request.setAttribute("Info",obj);
                    request.getRequestDispatcher("addnews.jsp").forward(request, response);
                    return;*/


            }
        }
        int i = info.sava(obj);
        if (i > 0) {
            System.out.println("添加成功");
            response.sendRedirect("MyJsp.jsp");
        } else {
            System.out.println("添加失败");
        }
    }


    protected void dele(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idstr = request.getParameter("id");
        System.out.println(">>>>>>>><<<<<<<<<<<"+idstr);

        int id = 0;

        try {
            id = Integer.parseInt(idstr);
        } catch (Exception e) {
            // TODO: handle exception
            id = 0;
        }

        int n = info.delete(id);
        if (n > 0) {
          /*  System.out.println("删除成功");
            response.sendRedirect("MyJsp.jsp");*/
            response.getWriter().write("success");
        } else {
            System.out.println("删除失败");
        }
    }


    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idstr=request.getParameter("idd");

        int id=0;

        try {
            id=Integer.parseInt(idstr);
        } catch (Exception e) {
            // TODO: handle exception
            id=0;
        }


        String newsTitle = request.getParameter("newsTitle") == null ? "" : request.getParameter("newsTitle");
        String newsType = request.getParameter("newsType") == null ? "0" : request.getParameter("newsType");

        String newsContent = request.getParameter("newsContent") == null ? "" : request.getParameter("newsContent");
        String sendUser = (String)request.getSession().getAttribute("login");

        String sendTime = request.getParameter("sendTime") == null ? "" : request.getParameter("sendTime");

        if (newsTitle.equals("")) {
            request.setAttribute("error", "请填写标题");
            request.getRequestDispatcher("addnews.jsp").forward(request,
                    response);
            return;
        }

        if (newsType.equals("0")) {
            request.setAttribute("error", "请选择类型");
            request.getRequestDispatcher("addnews.jsp").forward(request,
                    response);
            return;
        }
        if (newsContent.equals("")) {
            request.setAttribute("error", "请编写内容");
            request.getRequestDispatcher("addnews.jsp").forward(request,
                    response);
            return;
        }
        if (sendTime.equals("")) {
            request.setAttribute("error", "请选择时间");
            request.getRequestDispatcher("addnews.jsp").forward(request,
                    response);
            return;
        }
	/*UserDaoImpl userdaoimpl =new UserDaoImpl();*/
       /* ServiceUserImpl serviceUser=new ServiceUserImpl();
        serviceUser.queryByName(1);*/


        Info obj = new Info();
        obj.setNewsTitle(newsTitle);
        Type type = new Type();
        obj.setNewsId(id);

        type.setTypeId(1);
        obj.setNewsType(type);
        obj.setNewsContent(newsContent);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        User user = new User();
        user.setUserId(1);
        obj.setSendUser(user);
        try {
            obj.setSendTime(sdf.parse(sendTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ServiceInfoImpl info=new ServiceInfoImpl();

        int i = info.update(obj);
    }

}
