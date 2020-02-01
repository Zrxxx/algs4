# Union-Find (并查集)

## dynamic connectivity (动态连接)
  We assume ""is connect to" is an equivalence relation:
  - Reflexive: p is connected to p.
  - Symmetric: if p is connected to q, then q is connnected to p.
  - Transitive: if p is connected to q and q is connected to r,then p connected to r.
  (等价关系：自反、对称、传递)
  
  Connected components.  Maximal set of objects that are mutually connected.
  (连通分支)

  Find query.  Check if two objects are in the same component.
  Union command.  Replace components containing two objects with their union.


## quick-find
### public class QuickFindUF
{
  private int[] id;  
  public QuickFindUF(int N)
  {
    id = new int[N];
    for(int i = 0; i < N;i++)
      id[i]=i;
  }
  public boolean connected(int p,int q)
  { return id[p] == id[q];}
  public void union(int p,int q)
  {
    int pid = id[p];
    int qid = id[q];
    for(int i = 0; i < id.length; i++)
      if(id[i] == pid) id[i] = qid;
  }
}
union操作中，如果p,q的id值相同，则for循环将全部进行修改
## quick-union

## union-find
