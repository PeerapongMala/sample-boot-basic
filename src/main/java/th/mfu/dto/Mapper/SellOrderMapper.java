package th.mfu.dto.Mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Collection;
import java.util.List;

import th.mfu.domain.Product;
import th.mfu.domain.SellOrder;
import th.mfu.dto.ProductDTO;
import th.mfu.dto.SellOrderDTO;

@Mapper(componentModel="spring")
public interface SellOrderMapper {
@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSellOrderFromDto(SellOrderDTO sellOrder, @MappingTarget SellOrder newSellOrder);
    
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updateSellOrderFromEntity(List<SellOrder> entity,@MappingTarget List<SellOrderDTO> dto);

}
