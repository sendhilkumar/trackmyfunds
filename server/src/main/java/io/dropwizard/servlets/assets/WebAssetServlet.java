package io.dropwizard.servlets.assets;

import com.google.common.base.MoreObjects;
import com.google.common.io.Resources;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;

public class WebAssetServlet extends AssetServlet {
    public WebAssetServlet(String resourcePath, String uriPath, String indexFile, Charset defaultCharset) {
        super(resourcePath, uriPath, indexFile, defaultCharset);
    }

    protected URL getResourceUrl(String absoluteRequestedResourcePath) {
        ClassLoader loader = MoreObjects.firstNonNull(Thread.currentThread().getContextClassLoader(), Resources.class.getClassLoader());
        URL url = loader.getResource(absoluteRequestedResourcePath);
        if (url == null) {
            try {
                File base = new File(getResourceURL().toURI());
                return new File(base, getIndexFile()).toURI().toURL();
            } catch (URISyntaxException | MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        return url;
    }
}
