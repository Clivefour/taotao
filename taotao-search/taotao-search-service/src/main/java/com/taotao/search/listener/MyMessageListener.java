package com.taotao.search.listener;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private SearchService searchService;

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            String id = textMessage.getText();
            SearchItem item = tbItemMapper.findSearchItemById(Long.valueOf(id));
            searchService.addSearchItem(item);


        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
