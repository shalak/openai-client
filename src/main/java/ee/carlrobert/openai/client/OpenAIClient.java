package ee.carlrobert.openai.client;

import ee.carlrobert.openai.client.azure.AzureChatCompletionClient;
import ee.carlrobert.openai.client.azure.AzureClientRequestParams;
import ee.carlrobert.openai.client.azure.AzureTextCompletionClient;
import ee.carlrobert.openai.client.completion.chat.ChatCompletionClient;
import ee.carlrobert.openai.client.completion.text.TextCompletionClient;
import ee.carlrobert.openai.client.dashboard.DashboardClient;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class OpenAIClient {

  final String apiKey;
  final String host;
  final String organization;
  final Proxy proxy;
  final ProxyAuthenticator proxyAuthenticator;
  final Long connectTimeout;
  final TimeUnit connectTimeoutUnit;
  final Long readTimeout;
  final TimeUnit readTimeoutUnit;

  private OpenAIClient(Builder builder) {
    this.apiKey = builder.apiKey;
    this.host = builder.host;
    this.organization = builder.organization;
    this.proxy = builder.proxy;
    this.proxyAuthenticator = builder.proxyAuthenticator;
    this.connectTimeout = builder.connectTimeout;
    this.connectTimeoutUnit = builder.connectTimeoutUnit;
    this.readTimeout = builder.readTimeout;
    this.readTimeoutUnit = builder.readTimeoutUnit;
  }

  public String getHost() {
    return host;
  }

  public static class Builder {

    private final String apiKey;
    private String host;
    private String organization;
    private Proxy proxy;
    private ProxyAuthenticator proxyAuthenticator;
    private Long connectTimeout;
    private TimeUnit connectTimeoutUnit;
    private Long readTimeout;
    private TimeUnit readTimeoutUnit;

    public Builder(String apiKey) {
      this.apiKey = apiKey;
    }

    public Builder setHost(String host) {
      this.host = host;
      return this;
    }

    public Builder setOrganization(String organization) {
      this.organization = organization;
      return this;
    }

    public Builder setProxy(Proxy proxy) {
      this.proxy = proxy;
      return this;
    }

    public Builder setProxyAuthenticator(ProxyAuthenticator proxyAuthenticator) {
      this.proxyAuthenticator = proxyAuthenticator;
      return this;
    }

    public Builder setConnectTimeout(Long timeout, TimeUnit unit) {
      this.connectTimeout = timeout;
      this.connectTimeoutUnit = unit;
      return this;
    }

    public Builder setReadTimeout(Long timeout, TimeUnit unit) {
      this.readTimeout = timeout;
      this.readTimeoutUnit = unit;
      return this;
    }

    public ChatCompletionClient buildChatCompletionClient() {
      return new ChatCompletionClient(new OpenAIClient(this));
    }

    public TextCompletionClient buildTextCompletionClient() {
      return new TextCompletionClient(new OpenAIClient(this));
    }

    public DashboardClient buildDashboardClient() {
      return new DashboardClient(new OpenAIClient(this));
    }

    public AzureChatCompletionClient buildAzureChatCompletionClient(
        AzureClientRequestParams requestParams) {
      return new AzureChatCompletionClient(new OpenAIClient(this), requestParams);
    }

    public AzureTextCompletionClient buildAzureTextCompletionClient(
        AzureClientRequestParams requestParams) {
      return new AzureTextCompletionClient(new OpenAIClient(this), requestParams);
    }
  }
}
