package com.bjs.contract.service;

/**
 * @author Winter
 */
public interface CorrectionMarginService {
  /**
   * 检查一个用户是否没有任何持仓，如果没有的话，那么退回这个用户可能剩余的保证金
   */
  void correct(long uid);
}
