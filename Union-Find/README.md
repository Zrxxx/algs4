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
### union操作中，如果p,q分属于不同的连通分量，则for循环将p所在的连通分量的所有记录修改为q  
   Union too expensive (N array accesses). Trees are flat, but too expensive to keep them flat. 
   It takes N^2 array accesses to process a sequence of N union commands on N objects.

public class QuickFindUF  
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


## quick-union
### 连通分量用树状表示，id[]记录各个点的父节点。如果p,q分属于不同的连通分量，则将p的根节点作为q的根节点  
增加了一个root的函数：使用while找到树的根节点  
  Trees can get tall. ・Find too expensive (could be N array accesses).  
  
public class QuickFindUF  
{  
  private int[] id;  
  public QuickFindUF(int N)  
  {  
    id = new int[N];  
    for(int i = 0; i < N;i++)  
    id[i]=i;  
  }  
  pubilc int root(int i)  
  {  
    while(i != id[i]) i = id[i];  
    return i;  
  }
  public boolean connected(int p,int q)  
   { return root[p] == root[q];}  
  public void union(int p,int q)  
  {  
    int i = root(p);  
    int j = root(q);  
    id[i] = j;  
  }  
}

## weighted quick-union
### Modify quick-union to avoid tall trees.  
Keep track of size of each tree (number of objects).  
Balance by linking root of smaller tree to root of larger tree

in the union:
if (i == j) return;  
if(sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }  
else              { id[j] = i; sz[i] += sz[j]; }  

### Running time.  
Find:  takes time proportional to depth of p and q.  
Union:  takes constant time, given roots.
### Proposition.  
Depth of any node x is at most lg N.  
Pf.  When does depth of x increase? Increases by 1 when tree T1 containing x is merged into another tree T2.  
The size of the tree containing x at least doubles since | T 2 |  ≥  | T 1 |. Size of tree containing x can double at most lg N times.
