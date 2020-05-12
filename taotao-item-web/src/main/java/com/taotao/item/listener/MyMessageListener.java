package com.taotao.item.listener;

import com.taotao.constant.FreeMakerConstant;
import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamGroup;
import com.taotao.pojo.TbItemParamKey;
import com.taotao.service.ItemService;
import freemarker.core.ParseException;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyMessageListener implements MessageListener{
    @Autowired
    private ItemService itemService;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
    private Writer writer;
    @Override
    public void onMessage(Message message) {
//        TextMessage textMessage = (TextMessage) message;
//        try {
//            String itemId = textMessage.getText();
//            Long id = Long.valueOf(itemId);
//            TbItem tbItem = itemService.findTbItemById(id);
//            TbItemDesc itemDesc = itemService.findTbItemDescByItemId(id);
//            List<TbItemParamGroup> groupList = itemService.findTbItemGroupByItemId(id);
//            StringBuffer sb = new StringBuffer();
//            sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"0\" class=\"Ptable\">\n");
//            sb.append("    <tbody>\n");
//            for (TbItemParamGroup group:groupList) {
//                sb.append("        <tr>\n");
//                sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+group.getGroupName()+"</th>\n");
//                sb.append("        </tr>\n");
//                List<TbItemParamKey> paramKeys = group.getParamKeys();
//                for (TbItemParamKey paramKey:paramKeys) {
//                    sb.append("        <tr>\n");
//                    sb.append("            <td class=\"tdTitle\">"+paramKey.getParamName()+"</td>\n");
//                    sb.append("            <td>"+paramKey.getItemParamValue().getParamValue()+"</td>\n");
//                    sb.append("        </tr>\n");
//                }
//
//            }
//            sb.append("    </tbody>\n");
//            sb.append("</table>");
//
//            //我们的模板文件路径在 /WEB-INF/ftl之下
//            Configuration configuration = freeMarkerConfigurer.getConfiguration();
//            //确定我们的模板用item.ftl
//            Template template = configuration.getTemplate("item.ftl");
//            Map map = new HashMap();
//
//            map.put("item", new Item(tbItem));
//            map.put("itemDesc", itemDesc);
//
//            map.put("itemParam", sb.toString());
//            writer = new FileWriter(new File(FreeMakerConstant.HTML_OUT_PATH + id + ".html"));
//            template.process(map, writer);
//
//        } catch (JMSException e) {
//            e.printStackTrace();
//        } catch (MalformedTemplateNameException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (TemplateNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (TemplateException e) {
//            e.printStackTrace();
//        }finally {
//           if(writer!=null){
//               try {
//                   writer.close();
//               } catch (IOException e) {
//                   e.printStackTrace();
//               }
//           }
//        }
    }
}
