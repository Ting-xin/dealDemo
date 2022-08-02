new Vue({
    el: '#detail',

    methods: {
        handleNodeClick(data) {
            console.log(data);
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.submit();
        },
        handleCurrentChange(val) {
            this.page = val;
            this.submit();
        },
        handleRadioChange(val) {
            this.radio = val;
        },
        copy(val) {
            navigator.clipboard.writeText(val)
                .then(() => {
                    alert('Promo code already copied!');
                })
                .catch(err => {
                    console.log('Copied went wrong', err);
                })
        },
        submit() {
            let temp = "/brand?page=" + this.page + '&size=' + this.pageSize + '&orderType=' + this.radio;
            window.open(temp, '_self');
        }
    },
    data() {
        return {
            page: 1,
            pageSize: 10,
            total: 208882,
            radio: 'Popularity',
            // brand: {
            //     storeName: 'Afton Mountain',
            //     url: 'aftonmountain.com',
            //     logo: 'logo/afton-mountain.jpg',
            //     description: "Afton Mountain is a smaller bed and breakfasts company operating the e-commerce site aftonmountain.com. Afton Mountain sells its products and services in the bed and breakfasts industry. Afton Mountain is a less active brand when it comes to discounting and offering coupons.",
            //     cate: "Places & Travel,Lodging,Bed and Breakfasts",
            // },
            brand: '',
            treeData: [],
            defaultProps: {
                children: 'children',
                label: 'label'
            },
            radio: 'Deal',
            deal: '',
            coupon: ''
        };
    },
    mounted() {
        let storeName = location.href.split('?')[1].split('&')[0].split('=')[1];
        axios.get('/promo/' + storeName).then(res => {
            if (res.data.code === 0) {
                this.deal = res.data.data.deal;
                this.coupon = res.data.data.coupon;
            } else {
                console.log('get promo error.');
            }
        })
        axios.get('/brand/' + storeName).then(res => {
            if (res.data.code === 0) {
                this.brand = res.data.data;
                this.brand.logo = 'http://111.229.14.128:9001/' + this.brand.logo
                if (this.brand.cate) {
                    let tempArr = this.brand.cate.split(','), cur = {};
                    this.treeData.push(cur);
                    for (let i = 0; i < tempArr.length; i++) {
                        let temp = cur;
                        temp.label = tempArr[i];
                        if (i != tempArr.length - 1) {
                            cur.children = [{}];
                            cur = cur.children[0];
                        }
                    }
                }
            } else {
                console.log('get brand error.');
            }
        })
    }
})
