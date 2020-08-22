package operation;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.Map;

public class SearchData {
    public void get(RestHighLevelClient client) throws IOException {
        SearchSourceBuilder searchBuilder = SearchSourceBuilder.searchSource();
        SearchRequest request = new SearchRequest("customer").source(searchBuilder);
        // データ取得
        SearchHits hits = client.search(request, RequestOptions.DEFAULT).getHits();
        for(SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            // １レコードごとの値を設定
            String name = (String) sourceAsMap.get("name");
            // String post_date = (String) sourceAsMap.get("post_date");
            // String message = (String) sourceAsMap.get("message");

            // System.out.println(String.format("user:%s data:%s message:%s",user , post_date, message));
            System.out.println(String.format("name:%s",name));
        }
    }

    /**
     * 条件を指定して検索
     * @param client
     * @throws IOException
     */
    public void getBank(RestHighLevelClient client) throws IOException {
        SearchSourceBuilder searchBuilder = SearchSourceBuilder.searchSource();
        QueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("address", "road");
        searchBuilder.query(matchQueryBuilder);
        SearchRequest request = new SearchRequest("bank").source(searchBuilder);
        // データ取得
        SearchHits hits = client.search(request, RequestOptions.DEFAULT).getHits();
        for(SearchHit hit : hits) {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            // １レコードごとの値を設定
            String firstname = (String) sourceAsMap.get("firstname");
            String lastname = (String) sourceAsMap.get("lastname");
            String address = (String) sourceAsMap.get("address");
            String email = (String) sourceAsMap.get("email");

            System.out.println(String.format("firstname:%s lastname:%s address:%s email:%s",
                                            firstname, lastname, address, email));
        }
    }
}
