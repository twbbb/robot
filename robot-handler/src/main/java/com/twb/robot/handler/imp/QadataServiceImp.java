package com.twb.robot.handler.imp;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.twb.robot.common.dao.MessageReceiveTacheParamRepository;
import com.twb.robot.common.entity.MessageReceiveTacheParam;
import com.twb.robot.common.utils.StringConvertUtils;
import com.twb.robot.handler.QadataService;
import com.twb.robot.utils.HttpClientUtils;

@Service
public class QadataServiceImp implements QadataService {

	private static final Logger logger = LoggerFactory.getLogger(QadataServiceImp.class);

	@Autowired
	MessageReceiveTacheParamRepository messageReceiveTacheParamRepository;

	@Transactional(rollbackFor = Exception.class)
	@Override
	public void updateSwtcPrice() throws Exception {
		List<MessageReceiveTacheParam> list = messageReceiveTacheParamRepository.getMessageReceiveTacheParamList("swtcurl");
		if(list==null||list.isEmpty()){
			return ;
		}
		List<MessageReceiveTacheParam> listPrice = messageReceiveTacheParamRepository.getListByKeyCodeb("qa_data","swtc报价");
		if(listPrice==null||listPrice.isEmpty()){
			return;
		}
		MessageReceiveTacheParam priceTacheParam = listPrice.get(0);

		MessageReceiveTacheParam messageReceiveTacheParam = list.get(0);
		Map rspMap = HttpClientUtils.sendGet(messageReceiveTacheParam.getCodea());
		
		int  statusCode = (int) rspMap.get("statusCode");
		if (statusCode != 200)
		{
			return;
		}
		
		String body = (String) rspMap.get("responseBody");
		Map map = (Map) JSON.parse(body);
		boolean success = (boolean) map.get("success");
		List data = (List) map.get("data");
		BigDecimal price = new BigDecimal(StringConvertUtils.toString( data.get(1)));
		BigDecimal wave =  new BigDecimal(StringConvertUtils.toString(data.get(2)));
		BigDecimal highPrice = new BigDecimal(StringConvertUtils.toString( data.get(3)));
		BigDecimal lowPrice = new BigDecimal(StringConvertUtils.toString( data.get(4)));
		String priceStr = price.setScale(5, BigDecimal.ROUND_HALF_UP).toString();
		String highPriceStr = highPrice.setScale(5, BigDecimal.ROUND_HALF_UP).toString();
		String lowPriceStr = lowPrice.setScale(5, BigDecimal.ROUND_HALF_UP).toString();
		String waveStr = wave.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		String now =  (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date());
		
		String answer =String.format( "swtc价格:￥%s,涨幅:%s%%,当日最高价:￥%s,当日最低价:￥%s	\r\n【%s】",priceStr,waveStr,highPriceStr,lowPriceStr,now);
		
		priceTacheParam.setCodec(answer);
		priceTacheParam.setRemark(waveStr);
		
	}

}
