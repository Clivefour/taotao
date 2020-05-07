package com.taotao.search.service.impl;

import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.SearchItem;
import com.taotao.pojo.SearchResult;
import com.taotao.pojo.TaotaoResult;
import com.taotao.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Autowired
    private SolrServer solrServer;

    @Override
    public TaotaoResult importSolr() {
        try {
            //查询数据库的代码 查询数据库 得到的图片是多张图片
            List<SearchItem> searchItemAll = tbItemMapper.findSearchItemAll();
            for (SearchItem searchItem : searchItemAll) {
                SolrInputDocument document = new SolrInputDocument();
                document.addField("id", searchItem.getId());
                document.addField("item_title", searchItem.getTitle());
                document.addField("item_sell_point", searchItem.getSellPoint());
                //存储成为 Lucene结构的图片 也是多张图片
                document.addField("item_price", searchItem.getPrice());
                document.addField("item_image", searchItem.getImage());
                document.addField("item_category_name", searchItem.getCategoryName());
                document.addField("item_desc", searchItem.getItemDesc());
                solrServer.add(document);
            }
            solrServer.commit();
            return TaotaoResult.build(200,"导入成功");
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 6、返回TaotaoResult。
        return TaotaoResult.build(500,"导入失败");
    }

    @Override
    public SearchResult findItemSearch(String query, Integer page) {
        SearchResult result = new SearchResult();
        try {
            SolrQuery solrQuery = new SolrQuery();
            //用户输入的内容
            solrQuery.setQuery(query);
            //设置默认域 我们默认从 复制域里面搜索 标题（） 卖点 描述 分类
            solrQuery.set("df","item_keywords");
            //设置高亮
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("item_title");
            solrQuery.setHighlightSimplePre("<font style='color:red'>");
            solrQuery.setHighlightSimplePost("</font>");
            solrQuery.setStart((page-1)*60);
            solrQuery.setRows(60);
            //service写完

            //dao代码
            QueryResponse queryResponse = solrServer.query(solrQuery);
            //solr文档集合对象
            SolrDocumentList documentList = queryResponse.getResults();
            long totalCount = documentList.getNumFound();
            //设置总记录条数
            result.setTotalCount(totalCount);
            Long totalPages = (totalCount%60)==0?(totalCount/60):(totalCount/60)+1;
            //设置总页数
            result.setTotalPages(totalPages);
            List<SearchItem> itemList = new ArrayList<SearchItem>();
            for (SolrDocument document:documentList) {
                SearchItem item = new SearchItem();
                item.setId((String) document.get("id"));
                item.setCategoryName((String) document.get("item_category_name"));
                item.setImage((String) document.get("item_image"));
                item.setPrice((long) document.get("item_price"));
                item.setSellPoint((String) document.get("item_sell_point"));
                String item_title = "";
                Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
                List<String> list = highlighting.get(document.get("id")).get("item_title");
                if(list!=null&&list.size()>0){
                    item_title = list.get(0);
                }else{
                    item_title = (String) document.get("item_title");
                }
                item.setTitle(item_title);
                itemList.add(item);
            }

            result.setItemList(itemList);
            return result;
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addSearchItem(SearchItem item) {

        try {
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id", item.getId());
            document.addField("item_title", item.getTitle());
            document.addField("item_sell_point", item.getSellPoint());
            //存储成为 Lucene结构的图片 也是多张图片
            document.addField("item_price", item.getPrice());
            document.addField("item_image", item.getImage());
            document.addField("item_category_name", item.getCategoryName());
            document.addField("item_desc", item.getItemDesc());
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
