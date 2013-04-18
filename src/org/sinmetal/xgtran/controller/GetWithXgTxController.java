package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

public class GetWithXgTxController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		Transaction tx = DatastoreServiceFactory.getDatastoreService()
				.beginTransaction(TransactionOptions.Builder.withXG(true));

		final String hoge = asString("hoge");
		Entity entity = null;
		try {
			entity = Datastore
					.getOrNull(tx, KeyFactory.createKey("Hoge", hoge));
			tx.commit();
		} finally {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		}

		if (entity == null) {
			response.getWriter().println(
					String.format("DONE : Key = %s is null", hoge));
		} else {
			response.getWriter().println(
					String.format("DONE : Key = %s exist. ", hoge));
		}
		return null;
	}

}
