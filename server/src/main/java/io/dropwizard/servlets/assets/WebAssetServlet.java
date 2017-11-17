package io.dropwizard.servlets.assets;

import com.google.common.base.MoreObjects;
import com.google.common.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.nio.charset.Charset;

public class WebAssetServlet extends AssetServlet {
    private static Logger logger = LoggerFactory.getLogger(WebAssetServlet.class.getName());

    public WebAssetServlet(String resourcePath, String uriPath, String indexFile, Charset defaultCharset) {
        super(resourcePath, uriPath, indexFile, defaultCharset);
    }

    protected URL getResourceUrl(String absoluteRequestedResourcePath) {
        ClassLoader loader = MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader());
        URL url = loader.getResource(absoluteRequestedResourcePath);
        if (url == null) {
            try {
                return new URL(getResourceURL(), getIndexFile());
            } catch (Exception e) {
                logger.error("Couldn't serve " + absoluteRequestedResourcePath, e);
                throw new RuntimeException(e);
            }
        }
        return url;
    }
}
