/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.bigtop.datagenerators.bigpetstore.generators.customer;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.bigtop.datagenerators.bigpetstore.Constants;
import org.apache.bigtop.datagenerators.bigpetstore.datamodels.Store;
import org.apache.bigtop.datagenerators.locations.Location;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class TestCustomerLocationPDF
{

	@Test
	public void testProbability() throws Exception
	{
		List<Location> zipcodes = Arrays.asList(new Location[] {
				new Location("11111", Pair.of(1.0, 1.0), "AZ", "Tempte", 30000.0, 100),
				new Location("22222", Pair.of(2.0, 2.0), "AZ", "Phoenix", 45000.0, 200),
				new Location("33333", Pair.of(3.0, 3.0), "AZ", "Flagstaff", 60000.0, 300)
				});

		List<Store> stores = new ArrayList<Store>();
		for(int i = 0; i < zipcodes.size(); i++)
		{
			Store store = new Store(i, "Store_" + i, zipcodes.get(i));
			stores.add(store);
		}

		CustomerLocationPDF customerLocationPDF = new CustomerLocationPDF(zipcodes, stores.get(0),
					Constants.AVERAGE_CUSTOMER_STORE_DISTANCE);

		double prob = customerLocationPDF.probability(zipcodes.get(0));

		assertTrue(prob > 0.0);
	}

}