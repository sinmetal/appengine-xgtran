package org.sinmetal.xgtran.controller;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;

public class GetWithoutTxController extends Controller {

	@Override
	protected Navigation run() throws Exception {
		final String kind = asString("kind");
		final String name = asString("name");

		Entity entity = Datastore.getOrNullWithoutTx(KeyFactory.createKey(kind,
				name));

		if (entity == null) {
			response.getWriter().println(
					String.format("DONE : Kind = %s, Key = %s is null", kind,
							name));
		} else {
			response.getWriter().println(
					String.format("DONE : Kind = %s, Key = %s exist. ", kind,
							name));
		}
		return null;
	}
}
