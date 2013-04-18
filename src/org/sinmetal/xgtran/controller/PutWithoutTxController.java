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
		final String kind = asString("kind");
		final String name = asString("name");

		Key key = KeyFactory.createKey(kind, name);
		Entity entity = new Entity(key);
		Datastore.putWithoutTx(entity);
		return null;
	}

}
