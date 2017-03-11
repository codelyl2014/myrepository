package com.chexiang.controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.chexiang.entity.Workers;
import com.chexiang.service.WorkersService;

@Controller
@RequestMapping("/workers")
public class WorkersController {
	@Resource
	private WorkersService workersService;

	/*
	 * @RequestMapping("/list") public String getworkerlist(HttpServletResponse
	 * response) throws IOException {
	 * response.setContentType("text/html;charset=UTF-8"); List<Workers>
	 * getworkerslist = workersService.getWorkerList(); JSONObject
	 * workerlistJObject = new JSONObject(); String workerlistjson =
	 * workerlistJObject.toJSON(getworkerslist).toString();
	 * System.out.println(workerlistjson);
	 * response.getWriter().print(workerlistjson); response.getWriter().flush();
	 * response.getWriter().close();
	 * 
	 * return "showWorker"; }
	 */

	/*
	 * @RequestMapping("/list")
	 * 
	 * @ResponseBody public String workerlist() { List<Workers> getworkerslist =
	 * workersService.getWorkerList(); JSONObject workerlistJObject = new
	 * JSONObject(); String workerlistjson =
	 * workerlistJObject.toJSON(getworkerslist).toString();
	 * System.out.print(workerlistjson); // model.addAttribute("data",
	 * workerlistjson); return workerlistjson; }
	 */

	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView getworkerlist() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("showWorker");
		List<Workers> getworkerList = workersService.getWorkerList();
//		JSONObject worklist = new JSONObject();
//		String workString = worklist.toJSON(getworkerList).toString();
//		System.out.print(workString);
		// Iterator<Workers> iter =getworkerList.iterator();
		// while (iter.hasNext()) {
		// System.out.println(getworkerList);
		// }
		mav.addObject(getworkerList);
		return mav;
	}


}
