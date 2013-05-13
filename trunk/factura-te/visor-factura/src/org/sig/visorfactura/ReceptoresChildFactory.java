package org.sig.visorfactura;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.sig.derbyclient.dto.ReceptoresDto;

public class ReceptoresChildFactory extends ChildFactory<ReceptoresDto> {

    private List<ReceptoresDto> resultList;

    public ReceptoresChildFactory(List<ReceptoresDto> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected boolean createKeys(List<ReceptoresDto> list) {
        for (ReceptoresDto ReceptoresDto : resultList) {
            list.add(ReceptoresDto);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(ReceptoresDto c) {
       return new ReceptoresNode(c);
    }
}
