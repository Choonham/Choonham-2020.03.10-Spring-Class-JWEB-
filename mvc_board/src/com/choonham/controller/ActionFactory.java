package com.choonham.controller;

import com.choonham.controller.action.Action;
import com.choonham.controller.action.BoardCheckPassAction;
import com.choonham.controller.action.BoardCheckPassFormAction;
import com.choonham.controller.action.BoardDeleteAction;
import com.choonham.controller.action.BoardListAction;
import com.choonham.controller.action.BoardUpdateAction;
import com.choonham.controller.action.BoardUpdateFormAction;
import com.choonham.controller.action.BoardViewAction;
import com.choonham.controller.action.BoardWriteAction;
import com.choonham.controller.action.BoardWriteFormAction;

public class ActionFactory {

	private static ActionFactory instance = null;
	
	public ActionFactory() {
	}
	
	public static ActionFactory getInstance() {
		if(instance == null) {
			instance = new ActionFactory();
		}
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		
		if(command.equals("board_list")) {
			action = new BoardListAction();
		} else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		} else if(command.equals("board_write")) {
			action = new BoardWriteAction();
		} else if(command.equals("board_view")) {
			action = new BoardViewAction();
		} else if(command.equals("board_check_pass_form")) {
			action = new BoardCheckPassFormAction();
		} else if(command.equals("board_check_pass")) {
			action = new BoardCheckPassAction();
		} else if(command.equals("board_update_form")) {
			action = new BoardUpdateFormAction();
		} else if(command.equals("board_delete")) {
			action = new BoardDeleteAction();
		} else if(command.equals("board_update")) {
			action = new BoardUpdateAction();
		}
		return action;
	}

}
