package regexit.feeder.service.paging;

public class PageItem {
    private PageItemType pageItemType;

    private int index;

    private boolean active;

    public PageItem(PageItemType pageItemType, int index, boolean active) {
        this.pageItemType = pageItemType;
        this.index = index;
        this.active = active;
    }

    public PageItem() {
    }

    public PageItemType getPageItemType() {
        return pageItemType;
    }

    public void setPageItemType(PageItemType pageItemType) {
        this.pageItemType = pageItemType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static PageItemBuilder builder() {
        return new PageItemBuilder();
    }

    static class PageItemBuilder {
        private boolean active;
        private int index;
        private PageItemType pageItemType;

        public PageItemBuilder active(boolean active) {
            this.active = active;
            return this;
        }

        public PageItemBuilder index(int index) {
            this.index = index;
            return this;
        }

        public PageItemBuilder pageItemType(PageItemType itemType) {
            this.pageItemType = itemType;
            return this;
        }

        public PageItem build() {
            return new PageItem(this.pageItemType, this.index, this.active);
        }
    }
}
