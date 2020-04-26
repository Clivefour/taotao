package com.taotao.service.impl;

import com.taotao.constant.FTPConstant;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import com.taotao.utils.FtpUtil;
import com.taotao.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {
        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiResult findTbItemByPage(int page, int limit) {
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.findTbItemByCount();
        result.setCount(count);
        List<TbItem> data = tbItemMapper.findTbItemByPage((page-1)*limit,limit);
        result.setData(data);
        return result;
    }

    @Override
    public TaotaoResult updateItem(List<TbItem> tbItems, int type, Date date) {
        /**
         * 1.我们不知道 这个list集合 是有一个 还是多个  但是我们是要根据商品id来做修改
         */

        if(tbItems.size()<=0){
            //new TaotaoResult();
            return TaotaoResult.build(500,"请先勾选,在操作",null);
        }
        //需要修改的商品id
        List<Long> ids = new ArrayList<Long>();
        //吧需要修改的商品id 加入到list集合里面去
        for (TbItem tbItem: tbItems) {
            ids.add(tbItem.getId());
        }
        int count = tbItemMapper.updateItemByIds(ids,type,date);
        //count大于0才代表 我们修改了数据库里面的数据
        if(count>0&&type==0){
            return TaotaoResult.build(200,"商品下架成功",null);
        }else if(count>0&&type==1){
            return TaotaoResult.build(200,"商品上架成功",null);
        }else if(count>0&&type==2){
            return TaotaoResult.build(200,"商品删除成功",null);
        }




        return TaotaoResult.build(500,"商品修改",null);
    }

    @Override
    public LayuiResult getLikeItem(Integer page, Integer limit, String title, Integer priceMin, Integer priceMax, Long cId) {
        if(priceMin == null){
            priceMin = 0;
        }
        if(priceMax == null){
            priceMax = 100000000;
        }
        /**
         * 组装一下数据  然后 调用一个dao 得到结果集
         *
         */
        LayuiResult result = new LayuiResult();
        result.setCode(0);
        result.setMsg("");
        int count = tbItemMapper.findTbItemByLikeConut(title,priceMin,priceMax,cId);
        //设置查询结果集的数量
        result.setCount(count);
        List<TbItem> data = tbItemMapper.findTbItemByLike(title,priceMin,priceMax,cId,(page-1)*limit,limit);
        result.setData(data);

        return result;
    }

    @Override
    public PictureResult addPicture(String fileNmae, byte[] bytes) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        //以每天的日期做为 文件夹的名称 在这个文件夹里面存放图片
        String filePath = format.format(date);
        //随机生成一个字符串   本身的名字 只要.jpg 随机字符串.jpg
        String filename = IDUtils.genImageName()+fileNmae.substring(fileNmae.lastIndexOf("."));
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        boolean b = FtpUtil.uploadFile(FTPConstant.FTP_ADDRESS, FTPConstant.FTP_PORT, FTPConstant.FTP_USERNAME, FTPConstant.FTP_PASSWORD, FTPConstant.FILI_UPLOAD_PATH, filePath, filename, bis);
        if(b){
            PictureResult result = new PictureResult();
            result.setCode(0);
            result.setMsg("");
            PictureData data = new PictureData();
            data.setSrc(FTPConstant.IMAGE_BASE_URL+"/"+filePath+"/"+filename);
            result.setData(data);
            System.out.println(data.getSrc());
            return result;
        }
        return null;
    }
}
