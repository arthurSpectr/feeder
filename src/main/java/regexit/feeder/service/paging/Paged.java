package regexit.feeder.service.paging;

import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class Paged<T> {

    private Page<T> page;

    private Paging paging;

    public Paged(Page<T> page, Paging paging) {
        this.page = page;
        this.paging = paging;
    }

    public Paged() {
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
