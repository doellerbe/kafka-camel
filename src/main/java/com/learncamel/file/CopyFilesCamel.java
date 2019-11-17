package com.learncamel.file;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopyFilesCamel {

    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // from:data source URI
                    // to: data sink URI
                    // noop=true ensures the file doesn't get written to .camel in the input directory
                    from("file:data/input?noop=true").to("file:data/output");
                }
            });

            camelContext.start();
            Thread.sleep(5000);
            camelContext.stop();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
