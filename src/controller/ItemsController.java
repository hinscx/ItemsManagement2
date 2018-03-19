package controller;

import domain.Items;
import domain.PageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import domain.ItemsCustom;

import domain.ItemsQueryVo;
import service.ItemsService;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/items")
public class ItemsController {


    @Autowired
    @Qualifier("itemsService1")
    private ItemsService itemsService;

    @RequestMapping("/queryItems")
    public ModelAndView queryItems() throws Exception {
        //调用service来查询商品列表
        List<ItemsCustom> itemsList=itemsService.findItemsList(null);

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("itemsList",itemsList);
        //指定逻辑视图名itemsList
        modelAndView.setViewName("itemsList");

        return modelAndView;
    }

    @RequestMapping("/queryItemsPage")
    public void queryItemsPage(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        int pc = getPc(httpServletRequest);

        int pr = 10;

        PageBean<Items> pb = itemsService.findItemsListPage(pc, pr);
        pb.setUrl("/myssm/items/queryItemsPage.action?");

        httpServletRequest.setAttribute("pb", pb);

        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/itemsPagedList.jsp").forward(httpServletRequest, httpServletResponse);
    }

    private int getPc(HttpServletRequest request) {

        String value = request.getParameter("pc");
        if (value == null || value.trim().isEmpty()) {
            return 1;
        }
        return Integer.parseInt(value);
    }



//    @RequestMapping(value = "/editItems",method = RequestMethod.GET)
//    public ModelAndView editItems(@RequestParam int id) throws Exception {
//        ModelAndView modelAndView=new ModelAndView();
//
//        //调用service查询商品的信息
//        ItemsCustom itemsCustom=itemsService.findItemsById(id);
//        //将模型数据传到jsp
//        modelAndView.addObject("itemsCustom",itemsCustom);
//        //指定逻辑视图名
//        modelAndView.setViewName("editItems");
//
//        return modelAndView;
//    }

//    @RequestMapping(value = "/editItems",method = RequestMethod.GET)
//    public String editItems(Model model, @RequestParam int id) throws Exception {
//
//
//        //调用service查询商品的信息
//        ItemsCustom itemsCustom=itemsService.findItemsById(id);
//
//        model.addAttribute("itemsCustom",itemsCustom);
//
//        return "editItems";
//    }


    @RequestMapping(value = "/addItem")
    public void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception
    {

        request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request,response);
    }

    @RequestMapping("/addItemSubmit")
    public String addItemSubmit(ItemsCustom itemsCustom) throws Exception
    {


        itemsService.addItem(itemsCustom);
        //请求转发
//        return "forward:queryItems.action";



        //重定向
        return "success";
    }

    @RequestMapping(value = "/editItems",method = RequestMethod.GET)
    public void editItems(HttpServletRequest request, HttpServletResponse response, Integer id) throws Exception
    {

            //调用service查询商品的信息
            ItemsCustom itemsCustom = itemsService.findItemsById(id);

            request.setAttribute("itemsCustom", itemsCustom);

        //注意如果使用request转向页面，这里需要指定页面的完整路径
        request.getRequestDispatcher("/WEB-INF/jsp/editItems.jsp").forward(request,response);
    }

//    @RequestMapping("/editItemsSubmit")
//    public String editItemSubmit() throws Exception
//    {
////        请求转发,使用forward进行请求转发，request数据可以共享，url地址栏不会
////        return "forward:queryItems.action";
//
//        //使用redirect进行重定向，request数据无法共享，url地址栏会发生变化的。由于我们重定向的页面queryItems.action与本页面editItemSubmit.action在同一根目录下，所以不需要加入根路径
//        return "redirect:queryItems.action";
//    }

//
    //商品提交页面
    //itemsQueryVo是包装类型的pojo
//    @RequestMapping("/editItemsSubmit")
//    public String editItemSubmit(ItemsCustom itemsCustom) throws Exception
//    {
//
//        itemsService.updateItems(itemsCustom);
//        //请求转发
////        return "forward:queryItems.action";
//
//        //重定向
//        return "success";
//    }



    //商品提交页面
    //itemsQueryVo是包装类型的pojo
    @RequestMapping("/editItemsSubmit")
    public String editItemSubmit(Model model, @Validated @ModelAttribute(value = "itemsCustom") ItemsCustom itemsCustom,
                                 BindingResult bindingResult) throws Exception
    {
        //输出校验错误信息
        //如果参数绑定时出错
        if (bindingResult.hasErrors())
        {
            //获取错误
            List<ObjectError> errors=bindingResult.getAllErrors();

            model.addAttribute("errors",errors);

            for (ObjectError error:errors)
            {
                //输出错误信息
                System.out.println(error.getDefaultMessage());
            }

            //如果校验错误，仍然回到商品修改页面
            return "editItems";

        }
        else {
            itemsService.updateItems(itemsCustom);
            return "success";
        }


    }



    @RequestMapping("/deleteItem")
    public String deleteItem(Integer id) throws Exception {
        Integer[] singleId = {id};

        itemsService.deleteItems(singleId);
        return "success";
    }

    @RequestMapping("/deleteItems")
    public String deleteItems(Integer[] delete_id) throws Exception {
        //调用service方法删除商品
        //这里我们就是简单的介绍完成将页面的信息绑定到数组中，所以service的方法你可以自己去完成

        itemsService.deleteItems(delete_id);
        return "success";
    }



}

