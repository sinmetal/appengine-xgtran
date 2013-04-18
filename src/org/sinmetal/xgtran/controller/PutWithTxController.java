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
		final String kind = asString("kind");
		final String name = asString("name");

		Key key = KeyFactory.createKey(kind, name);
		Entity entity = new Entity(key);

		// 普通のTx
		Transaction tx = Datastore.beginTransaction();
		try {
			Key storedKey = Datastore.put(tx, entity);
			response.getWriter().println(
					String.format("stored key = %s", storedKey.getName()));
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
				response.getWriter().println("rollback.");
			}
		}
		response.getWriter().println(
				String.format("DONE Kind = %s, Key = %s", kind, name));
		return null;
	}

}
