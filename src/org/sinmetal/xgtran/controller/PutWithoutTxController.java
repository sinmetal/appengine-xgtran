package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class PutWithoutTxController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		Key key = KeyFactory.createKey("Hoge", asString("hoge"));
		Entity entity = new Entity(key);
		Datastore.putWithoutTx(entity);
		return null;
	}

}
