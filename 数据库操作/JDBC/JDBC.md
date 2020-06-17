####事务
数据库事务（Transaction）是由若干个 SQL 语句构成的一个操作序列，有点类似于 Java 的 synchronized 同步。数据库系统保证在一个事务中的所有 SQL 要么全部执行成功，要么全部不执行，即数据库事务具有 ACID 特性：

- Atomicity：原子性
- Consistency：一致性
- Isolation：隔离性
- Durability：持久性
