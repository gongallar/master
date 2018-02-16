package org.ebrick.system.response;

import org.ebrick.system.model.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class OrderSystem {

	
	/*Order order = new Order();
	order.setOrderId(5001);
	HttpHeaders requestHeaders = new HttpHeaders();
	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity<String> httpEntity = new HttpEntity<String>(asJsonString(order), requestHeaders);
	Order apiResponse = restTemplate.getForObject("http://localhost:8082/order/5001"+httpEntity, Order.class, 1);
	String consumerDetail = apiResponse.getBody();
	JSONParser jsonParser = new JSONParser();
	JSONObject jsonObject = (JSONObject) jsonParser.parse(consumerDetail);
	consumerDetailList = (List<PersonalDetail>) jsonObject.get(Constant.DATA);*/

}
