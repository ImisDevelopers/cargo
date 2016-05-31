/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2015 Ali Tokmen.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ========================================================================
 */
package org.codehaus.cargo.container.tomcat;

/**
 * Gathers all Tomcat properties.
 * 
 */
public interface TomcatPropertySet
{
    /**
     * The boolean value for the empty session path connector property.
     */
    String CONNECTOR_EMPTY_SESSION_PATH = "cargo.tomcat.connector.emptySessionPath";

    /**
     * URI Encoding to set.
     */
    String URI_ENCODING = "cargo.tomcat.uriencoding";

    /**
     * Port tomcat will listen on for AJP 1.3 requests.
     */
    String AJP_PORT = "cargo.tomcat.ajp.port";

    /**
     * Whether the contexts for deployed Web applications should be set as reloadable.
     */
    String CONTEXT_RELOADABLE = "cargo.tomcat.context.reloadable";

    /**
     * Whether the contexts for deployed Web applications should allow multipart parsing.
     */
    String CONTEXT_ALLOWMULTIPART = "cargo.tomcat.context.allowCasualMultipartParsing";

    /**
     * Whether WAR deployables should be copied or referenced.
     */
    String COPY_WARS = "cargo.tomcat.copywars";

    /**
     * The <code>webapps</code> directory of Tomcat.
     */
    String WEBAPPS_DIRECTORY = "cargo.tomcat.webappsDirectory";

    /**
     * Whether the HTTP container is secured.
     */
    String HTTP_SECURE = "cargo.tomcat.httpSecure";

    /**
     * Whether to use use http only.
     */
    String USE_HTTP_ONLY = "cargo.tomcat.useHttpOnly";

    /**
     * The file path for the key store file.
     */
    String CONNECTOR_KEY_STORE_FILE = "cargo.tomcat.connector.keystoreFile";

    /**
     * The type of the key store file.
     */
    String CONNECTOR_KEY_STORE_TYPE = "cargo.tomcat.connector.keystoreType";

    /**
     * The password for the server key store.
     */
    String CONNECTOR_KEY_STORE_PASSWORD = "cargo.tomcat.connector.keystorePass";

    /**
     * The alias of the key in the key store.
     */
    String CONNECTOR_KEY_ALIAS = "cargo.tomcat.connector.keyAlias";

    /**
     * The file path for the trust store file.
     */
    String CONNECTOR_TRUST_STORE_FILE = "cargo.tomcat.connector.truststoreFile";

    /**
     * The type of the trust store file.
     */
    String CONNECTOR_TRUST_STORE_TYPE = "cargo.tomcat.connector.truststoreType";

    /**
     * The password for the server trust store.
     */
    String CONNECTOR_TRUST_STORE_PASSWORD = "cargo.tomcat.connector.truststorePass";

    /**
     * The requirements for client authentication.
     */
    String CONNECTOR_CLIENT_AUTH = "cargo.tomcat.connector.clientAuth";

    /**
     * The password for the server key store.
     */
    String CONNECTOR_SSL_PROTOCOL = "cargo.tomcat.connector.sslProtocol";

    /**
     * Custom valves defined as properties separated by <code>|</code><br>
     * Maven example:<br>
     * <code>
     * &lt;cargo.tomcat.valve.stuckthread&gt;<br>
     * &nbsp; &nbsp; className=org.apache.catalina.valves.StuckThreadDetectionValve|<br>
     * &nbsp; &nbsp; threshold=60<br>
     * &lt;/cargo.tomcat.valve.stuckthread&gt;
     * </code>
     */
    String CUSTOM_VALVE = "cargo.tomcat.valve";
}
