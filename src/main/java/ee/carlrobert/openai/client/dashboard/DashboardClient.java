package ee.carlrobert.openai.client.dashboard;

import ee.carlrobert.openai.client.BaseClient;
import ee.carlrobert.openai.client.ClientCode;
import ee.carlrobert.openai.client.OpenAIClient;
import ee.carlrobert.openai.client.dashboard.response.Subscription;
import java.util.function.Consumer;
import okhttp3.Headers;
import okhttp3.Request;

public class DashboardClient extends BaseClient {

  private final String baseUrl;

  public DashboardClient(OpenAIClient client) {
    super(client);
    this.baseUrl = client.getBaseUrl() + "/dashboard/billing";
  }

  @Override
  protected ClientCode getClientCode() {
    return ClientCode.DASHBOARD;
  }

  public void getSubscriptionAsync(Consumer<Subscription> responseConsumer) {
    buildClient()
        .newCall(buildGetRequest(baseUrl + "/subscription"))
        .enqueue(new DashboardResponseCallback<>(responseConsumer, Subscription.class));
  }

  private Request buildGetRequest(String url) {
    return new Request.Builder()
        .url(url)
        .headers(Headers.of(headers))
        .get()
        .build();
  }
}
