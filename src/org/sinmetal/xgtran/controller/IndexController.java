package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

public class IndexController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		Transaction tx = DatastoreServiceFactory.getDatastoreService()
				.beginTransaction(TransactionOptions.Builder.withXG(true));
		try {
			Datastore.getOrNull(tx,
					KeyFactory.createKey("Hoge", asString("hoge")));
			Datastore.getOrNull(tx,
					KeyFactory.createKey("Fuga", asString("fuga")));
			Datastore.getOrNull(tx,
					KeyFactory.createKey("Moga", asString("moga")));

			Key logKey = KeyFactory.createKey("HogeLog", asString("log"));
			Entity logEntity = new Entity(logKey);
			Datastore.put(tx, logEntity);

			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

		response.getWriter().write("DONE");
		return null;
	}
}
