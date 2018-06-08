package cn.su.exam;

/**
 * @author suyulong
 */
public interface Account {
    /**
     * 单笔转账
     *
     * @param transferDTO
     * @return
     */
    Result transfer(TransferDTO transferDTO);
}

