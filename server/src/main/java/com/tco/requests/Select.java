package com.tco.requests;

public class Select {
		static String match(String match, int limit) {
			//return statement(match, "DISTINCT " + "id,name,municipality,iso_region,iso_country,continent,latitude,longitude,altitude" , "LIMIT " + limit);
            return statement(match, "world.name,world.municipality,region.name,country.name,continent.name,latitude,longitude,altitude", "LIMIT " + limit);
		}
    
    static String found(String match) {
			return statement(match, "COUNT(*) AS count ", "");
		}
    
    static String statement(String match, String data, String limit) {
			return "SELECT "
				+ data
				+ " FROM " + "world INNER JOIN region ON world.iso_region = region.id INNER JOIN country ON world.iso_country = country.id INNER JOIN continent on world.continent = continent.id"
                //+ " FROM " + "world"
				+ " WHERE (country.name LIKE \"%" + match + "%\" OR region.name LIKE \"%" + match + "%\" OR world.name LIKE \"%" + match + "%\" OR world.municipality LIKE \"%" + match + "%\")"
                //+ " WHERE name LIKE \"%" + match + "%\""
				+ limit
				+ " ;";
		}
	}
   
