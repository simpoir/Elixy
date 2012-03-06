/*------------------------------------------------------------------------------
# Copyright (C) 2012  Simon Poirier  <simpoir@gmail.com>
# 
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
------------------------------------------------------------------------------*/
/**
 *
 * Base naming rule:
 * The stuff start with "_" means private , end with "_" means protect ,
 * others mean public.
 *
 * All the member field should be private.
 *
 * Life cycle: (It's very important to know when we bind the event)
 * A widget will do this by order :
 * 1. $init
 * 2. set attributes (setters)
 * 3. rendering mold (@see mold/term.js )
 * 4. call bind_ to bind the event to dom .
 *
 * this.deskop will be assigned after super bind_ is called,
 * so we use it to determine whether we need to update view
 * manually in setter or not.
 * If this.desktop exist , means it's after mold rendering.
 *
 */
term.VirtTerm = zk.$extends(zul.Widget, {
	_text:'', //default value for text attribute
	_vt:null,
	_rows:0,
	_cols:0,

	/**
	 * Don't use array/object as a member field, it's a restriction for ZK object,
	 * it will work like a static , share with all the same Widget class instance.
	 *
	 * if you really need this , assign it in bind_ method to prevent any trouble.
	 *
	 * TODO:check array or object , must be one of them ...I forgot. -_- by Tony
	 */
	
	$define: {
		/**
		 * The member in $define means that it has its own setter/getter.
		 * (It's a coding sugar.)
		 *
		 * If you don't get this ,
		 * you could see the comment below for another way to do this.
		 *
		 * It's more clear.
		 *
		 */
		text: function() { //this function will be called after setText() .
		
			if(this.desktop) {
				//updated UI here.
				this._vt.write(this._text);
				this._text = "";
			}
		},
		rows: function() {},
		cols: function() {}
	},
	
	bind_: function () {
		/**
		 * For widget lifecycle , the super bind_ should be called
		 * as FIRST STATEMENT in the function.
		 * DONT'T forget to call supers in bind_ , or you will get error.
		 */
		this.$supers(term.VirtTerm,'bind_', arguments);
	
		//A example for domListen_ , REMEMBER to do domUnlisten in unbind_.
		//this.domListen_(this.$n("cave"), "onClick", "_doItemsClick");
		this._vt = new VT100(this._cols, this._rows, this.uuid);
		this._vt.curs_set(true, true, document.getElementById("term"));
		var self = this;
		this._vt.getch(function(ch, t) {
			if (ch === undefined) {
				return;
			}
			self.fire("onType", {chr: ch});
			t.getch(arguments.callee);
		});
	},
	
	/*
	 * A example for domListen_ listener.
	 */
	/*
	 * _doItemsClick: function (evt) { alert("item click event fired"); },
	 */
	unbind_: function () {
	
		// A example for domUnlisten_ , should be paired with bind_
		// this.domUnlisten_(this.$n("cave"), "onClick", "_doItemsClick");
		
		
		/*
		* For widget lifecycle , the super unbind_ should be called
		* as LAST STATEMENT in the function.
		*/
		this.$supers(term.VirtTerm,'unbind_', arguments);
	},
	
	getZclass: function () {
		return this._zclass != null ? this._zclass: "z-virtterm";
	}
});
