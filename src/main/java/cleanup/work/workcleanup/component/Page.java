package cleanup.work.workcleanup.component;


import org.springframework.data.domain.Pageable;

public class Page {

    private final int PAGEGROUP = 5;    // 페이지의 묶음
    private Pageable pageable;
    private Long totalCount = 0L;


    private int endPage;
    private int beginPage;
    private int pageNumber;


    public Page(Long totalCount, Pageable pageable) {
        this.totalCount = totalCount;
        this.pageable = pageable;
        this.beginPage = calcBeginPage();
        this.pageNumber = pageable.getPageNumber();
        this.endPage = calcEndPage();
        if(calcEndPage() > calcRealEndPage()) this.endPage = calcRealEndPage();
        if(calcBeginPage() >= calcRealEndPage()) this.endPage = calcBeginPage();
    }


    private int calcBeginPage() {
        return calcEndPage() - (PAGEGROUP - 1);
    }

    private int calcRealEndPage() {
        return (int) (Math.ceil((double) totalCount) / pageable.getPageSize());
    }

    private int calcEndPage() {
        return (int) Math.ceil((pageable.getPageNumber() + 1) / ((double) PAGEGROUP)) * PAGEGROUP;
    }

    public int getEndPage() {
        return endPage;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}
