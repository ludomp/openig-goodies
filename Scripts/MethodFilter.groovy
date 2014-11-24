/**
 * The contents of this file are subject to the terms of the Common Development and
 * Distribution License 1.0 (the License). You may not use this file except in compliance with the
 * License.
 * Copyright 2014 ForgeRock AS.
 */

import org.forgerock.openig.http.Response
 
/*
 * Filters requests that have the allowedmethods supplied using a 
 * configuration like the following:
 *
 * {
 *     "name": "MethodFilter",
 *     "type": "ScriptableFilter",
 *     "config": {
 *         "type": "application/x-groovy",
 *         "file": "MethodFilter.groovy",
 *         "args": {
 *             "allowedmethods": [ "GET", "HEAD" ]
 *         }
 *     }
 * }
 */
 

if (allowedmethods.contains(exchange.request.method)) {
    // Call the next handler. This returns when the request has been handled.
    next.handle(exchange)
} else {
    exchange.response = new Response()
    exchange.response.status = 405
    exchange.response.reason = "Method not allowed: (" + exchange.request.method +")"
    exchange.response.headers.addAll("Allow", allowedmethods)  
}
     
