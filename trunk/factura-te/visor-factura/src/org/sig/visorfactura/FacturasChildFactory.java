package org.sig.visorfactura;

import java.util.List;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import org.sig.derbyclient.dto.FacturaDto;

public class FacturasChildFactory extends ChildFactory<FacturaDto> {

    private List<FacturaDto> resultList;

    public FacturasChildFactory(List<FacturaDto> resultList) {
        this.resultList = resultList;
    }

    @Override
    protected boolean createKeys(List<FacturaDto> list) {
        for (FacturaDto FacturaDto : resultList) {
            list.add(FacturaDto);
        }
        return true;
    }

    @Override
    protected Node createNodeForKey(FacturaDto c) {
        return new FacturaNode(c);
    }
}
