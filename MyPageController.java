package jp.co.internous.quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.internous.quest.model.mapper.MstUserMapper;
import jp.co.internous.quest.model.domain.MstUser;
import jp.co.internous.quest.model.session.LoginSession;

@Controller
@RequestMapping("/quest/mypage")
public class MyPageController {

	@Autowired
	private MstUserMapper mstUserMapper;

	@Autowired
	private LoginSession loginSession;

	@RequestMapping("/")
	public String index(Model m) {
		MstUser mstUser = mstUserMapper.findByUserNameAndPassword(loginSession.getUserName(), loginSession.getPassword());
		m.addAttribute("mstUser", mstUser);
		m.addAttribute("loginSession", loginSession);
		return "my_page";
	}

}