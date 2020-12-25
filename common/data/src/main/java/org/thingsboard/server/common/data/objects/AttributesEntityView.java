package org.thingsboard.server.common.data.objects;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Victor Basanets on 9/05/2017.
 */
@Data
@NoArgsConstructor
public class AttributesEntityView implements Serializable {

    private List<String> cs = new ArrayList<>();
    private List<String> ss = new ArrayList<>();
    private List<String> sh = new ArrayList<>();

    public AttributesEntityView(List<String> cs,
                                List<String> ss,
                                List<String> sh) {

        this.cs = new ArrayList<>(cs);
        this.ss = new ArrayList<>(ss);
        this.sh = new ArrayList<>(sh);
    }

    public AttributesEntityView(AttributesEntityView obj) {
        this(obj.getCs(), obj.getSs(), obj.getSh());
    }
}
