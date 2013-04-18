package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

public class GetWithoutTxController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		final String hoge = asString("hoge");
		Entity entity = Datastore.getOrNullWithoutTx(KeyFactory.createKey(
				"Hoge", hoge));

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
