package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;

public class PutWithTxController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		Key key = KeyFactory.createKey("Hoge", asString("hoge"));
		Entity entity = new Entity(key);

		// 普通のTx
		Transaction tx = Datastore.beginTransaction();
		try {
			Datastore.put(tx, entity);
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}
		response.getWriter().println("DONE");
		return null;
	}

}
