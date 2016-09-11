/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol, 2012-2016 Ali Tokmen.
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
package org.codehaus.cargo.container.geronimo;

import java.io.File;

import org.apache.tools.ant.types.FilterChain;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.LocalContainer;
import org.codehaus.cargo.container.configuration.ConfigurationCapability;
import org.codehaus.cargo.container.geronimo.internal.AbstractGeronimoStandaloneLocalConfiguration;
import org.codehaus.cargo.container.geronimo.internal.Geronimo2xStandaloneLocalConfigurationCapability;

/**
 * Geronimo 2.x series standalone {@link org.codehaus.cargo.container.configuration.Configuration}
 * implementation.
 */
public class Geronimo2xStandaloneLocalConfiguration extends
    AbstractGeronimoStandaloneLocalConfiguration
{
    /**
     * Geronimo configuration capability.
     */
    private static ConfigurationCapability capability =
        new Geronimo2xStandaloneLocalConfigurationCapability();

    /**
     * {@inheritDoc}
     * @see AbstractGeronimoStandaloneLocalConfiguration#AbstractGeronimoStandaloneLocalConfiguration(String)
     */
    public Geronimo2xStandaloneLocalConfiguration(String dir)
    {
        super(dir);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigurationCapability getCapability()
    {
        return capability;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doConfigure(LocalContainer container) throws Exception
    {
        setupConfigurationDir();

        FilterChain filterChain = createGeronimoFilterChain(container);

        final String varDirectory = getHome() + "/var";
        if (!getFileHandler().exists(varDirectory))
        {
            getFileHandler().createDirectory(getHome(), "/var");

            // The /var directory does not exist, create it
            final String originalVarDirectory = ((InstalledLocalContainer) container).getHome()
                + "/var";
            getFileHandler().copyDirectory(originalVarDirectory, varDirectory);

            String securityDir = getFileHandler().createDirectory(getHome(), "/var/security");
            getResourceUtils().copyResource(
                RESOURCE_PATH + container.getId() + "/users.properties",
                new File(securityDir, "users.properties"), filterChain, "ISO-8859-1");
            getResourceUtils().copyResource(
                RESOURCE_PATH + container.getId() + "/groups.properties",
                new File(securityDir, "groups.properties"), filterChain, "ISO-8859-1");

            getFileHandler().createDirectory(getHome(), "/var/deploy");
            getFileHandler().createDirectory(getHome(), "/var/temp");
        }
    }
}
