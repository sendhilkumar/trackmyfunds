package io.dropwizard.assets;

import io.dropwizard.servlets.assets.AssetServlet;
import io.dropwizard.servlets.assets.WebAssetServlet;

import java.nio.charset.StandardCharsets;

public class WebAssetsBundle extends AssetsBundle {
    public WebAssetsBundle() {
    }

    public WebAssetsBundle(String path) {
        super(path);
    }

    public WebAssetsBundle(String resourcePath, String uriPath) {
        super(resourcePath, uriPath);
    }

    public WebAssetsBundle(String resourcePath, String uriPath, String indexFile) {
        super(resourcePath, uriPath, indexFile);
    }

    public WebAssetsBundle(String resourcePath, String uriPath, String indexFile, String assetsName) {
        super(resourcePath, uriPath, indexFile, assetsName);
    }

    @Override
    protected AssetServlet createServlet() {
        return new WebAssetServlet(getResourcePath(), getUriPath(), getIndexFile(), StandardCharsets.UTF_8);
    }
}
