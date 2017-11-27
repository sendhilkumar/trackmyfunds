package tracker.server;

import com.codahale.metrics.annotation.Timed;
import tracker.data.funds.loader.HistoricalNAVLoader;
import tracker.domain.LatestNAVDateFinder;
import tracker.domain.LatestNAVDateList;
import tracker.domain.NAVLoadFinder;
import tracker.domain.NAVLoadList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminService {

    @Path("loadNAV")
    @POST
    @Timed
    public void loadNAV(@QueryParam("from") String from, @QueryParam("to") String to) throws IOException {
        //yyyy-mm-dd format date
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {
                if (from != null && to != null) {
                    try {
                        new HistoricalNAVLoader().load(from, to);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    throw new RuntimeException("Both 'from' and 'to' date query params required. from:" + from + "; to:" + to);
                }
            }
        });
    }

    @Path("loadAllNAV")
    @POST
    @Timed
    public List<String> loadAllNAV(@QueryParam("from") String from) throws IOException {
        Runnable runnable = () -> {
            try {
                new HistoricalNAVLoader().load(from);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
        Executors.newSingleThreadExecutor().submit(runnable);
        return new ArrayList<>();
    }

    @Path("navLoad")
    @POST
    @Timed
    public NAVLoadList navLoadStats() {
        return NAVLoadFinder.findMany(NAVLoadFinder.all());
    }

    @Path("latestNAVDate")
    @POST
    @Timed
    public LatestNAVDateList latestNAVDate() {
        return LatestNAVDateFinder.findMany(LatestNAVDateFinder.all());
    }
}
