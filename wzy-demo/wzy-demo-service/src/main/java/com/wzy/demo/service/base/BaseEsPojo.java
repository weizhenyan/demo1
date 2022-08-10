package com.wzy.demo.service.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.zxp.esclientrhl.annotation.ESMapping;
import org.zxp.esclientrhl.enums.DataType;

import java.io.Serializable;

/**
 * BaseEsPojo
 *
 * @author 飞燕
 * @date   2022/8/8 18:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEsPojo implements Serializable {

    @ESMapping(datatype = DataType.text_type)
    private Integer state;
}
