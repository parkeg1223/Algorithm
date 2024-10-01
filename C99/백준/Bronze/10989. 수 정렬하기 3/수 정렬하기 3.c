#include <stdio.h>
#include <stdlib.h>

int main() {
    int num = 0, ntemp = 0;
    int min = 0, max = 0;
    int *numlist;
    scanf("%d", &num);

    numlist = (int *)calloc(sizeof(int), 10001);
    scanf("%d\n", &ntemp);
    min = max = ntemp;
    numlist[ntemp]++;
    for (int i = 1; i < num; i++) {
        scanf("%d", &ntemp);
        if (min > ntemp) min = ntemp;
        if (max < ntemp) max = ntemp;
        numlist[ntemp]++;
    }
    
    for (int i = min; i <= max; i++) {
        for (int j = 0; j < numlist[i]; j++) {
            printf("%d\n", i);
        }
    }
    
    free(numlist);
}