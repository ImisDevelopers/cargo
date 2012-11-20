/*
 * ========================================================================
 *
 * Codehaus CARGO, copyright 2004-2011 Vincent Massol.
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
package org.codehaus.cargo.sample.java;

import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.cargo.container.Container;
import org.codehaus.cargo.container.InstalledLocalContainer;
import org.codehaus.cargo.container.configuration.Configuration;
import org.codehaus.cargo.container.configuration.entry.DataSourceFixture;
import org.codehaus.cargo.container.deployable.Deployable;
import org.codehaus.cargo.container.deployable.DeployableType;
import org.codehaus.cargo.container.property.DatasourcePropertySet;
import org.codehaus.cargo.generic.deployable.DefaultDeployableFactory;
import org.codehaus.cargo.util.CargoException;

/**
 * Abstract test case for container with datasource capabilities.
 * 
 * @version $Id$
 */
public abstract class AbstractDataSourceWarCapabilityContainerTestCase extends
    AbstractCargoTestCase
{
    /**
     * Initializes the test case.
     * @param testName Test name.
     * @param testData Test environment data.
     * @throws Exception If anything goes wrong.
     */
    public AbstractDataSourceWarCapabilityContainerTestCase(String testName,
        EnvironmentTestData testData) throws Exception
    {
        super(testName, testData);
    }

    /**
     * Adds datasource and Tests servlet.
     * @param fixture Datasource definition.
     * @param type WAR type.
     * @throws MalformedURLException If URL cannot be built.
     */
    protected void testServletThatIssuesGetConnectionFrom(DataSourceFixture fixture, String type)
        throws MalformedURLException
    {
        addDataSourceToConfigurationViaProperty(fixture);

        testWar(type);
    }

    /**
     * Tests servlet.
     * @param type WAR type.
     * @throws MalformedURLException If URL cannot be built.
     */
    protected void testWar(String type) throws MalformedURLException
    {
        Deployable war =
            new DefaultDeployableFactory().createDeployable(getContainer().getId(), getTestData()
                .getTestDataFileFor(type + "-war"), DeployableType.WAR);

        getLocalContainer().getConfiguration().addDeployable(war);

        URL warPingURL =
            new URL("http://localhost:" + getTestData().port + "/" + type + "-war/test");

        startAndStop(warPingURL);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Container createContainer(Configuration configuration)
    {
        InstalledLocalContainer container =
            (InstalledLocalContainer) super.createContainer(configuration);
        setUpDerby(container);
        return container;
    }

    /**
     * Add datasource.
     * @param fixture Datasource definition.
     */
    public void addDataSourceToConfigurationViaProperty(DataSourceFixture fixture)
    {
        Configuration config = getLocalContainer().getConfiguration();
        config.setProperty(DatasourcePropertySet.DATASOURCE, fixture
            .buildDataSourcePropertyString());
    }

    /**
     * Setup a Derby datasource.
     * @param container Container to set on.
     */
    private void setUpDerby(InstalledLocalContainer container)
    {
        if ("glassfish3x".equals(container.getId()))
        {
            // GlassFish 3.x already ships with Derby, adding the JAR twice will result in
            // java.lang.SecurityException: sealing violation: package org.apache.derby.
            return;
        }

        String jdbcdriver = System.getProperty("cargo.testdata.derby-jar");
        if (jdbcdriver != null)
        {
            container.addExtraClasspath(jdbcdriver);
        }
        else
        {
            throw new CargoException(
                "Please set property [cargo.testdata.derby-jar] to a valid location of derby.jar");
        }
        container.getSystemProperties().put("derby.system.home", getTestData().targetDir);
        container.getSystemProperties().put("derby.stream.error.logSeverityLevel", "0");
    }

    /**
     * Start, test and stop WAR.
     * @param warPingURL WAR ping URL.
     */
    public void startAndStop(URL warPingURL)
    {
        getLocalContainer().start();
        PingUtils.assertPingTrue(warPingURL.getPath() + " not started", warPingURL, getLogger());

        getLocalContainer().stop();
        PingUtils.assertPingFalse(warPingURL.getPath() + " not stopped", warPingURL, getLogger());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tearDown()
    {
        try
        {
            getLocalContainer().stop();
        }
        finally
        {
            super.tearDown();
        }
    }
}
