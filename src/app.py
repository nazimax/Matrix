
import numpy as np

corr = open("./corrMatrix", "r+")
a = corr.read()

A = np.mat(a)

print "A : \n",A

print "Eigenvalues :",np.linalg.eigvals(A)

eigenvalue,eigenvector = np.linalg.eig(A)

eigVal = open("./eigenValues", "w")
eigVec = open("./eigenVectors", "w")


for i in eigenvalue:

    eigVal.write(str('{:f}'.format(i))+";")



for j in eigenvector:
    eigVec.write(str(j)+"\n")


corr.close()
eigVal.close()
eigVec.close()


