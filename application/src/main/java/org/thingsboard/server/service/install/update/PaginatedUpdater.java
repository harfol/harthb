package org.thingsboard.server.service.install.update;

import org.thingsboard.server.common.data.SearchTextBased;
import org.thingsboard.server.common.data.id.UUIDBased;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.PageLink;

public abstract class PaginatedUpdater<I, D extends SearchTextBased<? extends UUIDBased>> {

    private static final int DEFAULT_LIMIT = 100;

    public void updateEntities(I id) {
        PageLink pageLink = new PageLink(DEFAULT_LIMIT);
        boolean hasNext = true;
        while (hasNext) {
            PageData<D> entities = findEntities(id, pageLink);
            for (D entity : entities.getData()) {
                updateEntity(entity);
            }
            hasNext = entities.hasNext();
            if (hasNext) {
                pageLink = pageLink.nextPageLink();
            }
        }
    }

    protected abstract PageData<D> findEntities(I id, PageLink pageLink);

    protected abstract void updateEntity(D entity);

}
