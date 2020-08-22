import operation.SearchData;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("引数を指定してください");
        }

        RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                new HttpHost("localhost", 9200, "http")));
        try (client) {
            // TODO: この辺りはあとで変更したい
            switch (args[0]) {
                case "SEARCH": {
                    var searchData = new SearchData();
                    searchData.get(client);
                    break;
                }
                case "SEARCH-BANK": {
                    var searchData = new SearchData();
                    searchData.getBank(client);
                    break;
                }
                default: {
                    client.close();
                    throw new IllegalArgumentException("指定した引数には想定外の値が指定されています");
                }
            }
        }
    }
}

