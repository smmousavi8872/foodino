package com.developer.smmousavi.foodino.repositories;

import com.developer.smmousavi.foodino.models.Banner;
import com.developer.smmousavi.foodino.models.Category;
import com.developer.smmousavi.foodino.models.Product;
import com.developer.smmousavi.foodino.models.Recipe;
import com.developer.smmousavi.foodino.models.Specifications;
import com.developer.smmousavi.foodino.network.clients.HomeApiClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class HomeRepository {

    private static HomeRepository sInstance;

    private HomeApiClient mHomeApiClient;
    private String mPreviewRecipesQuery;
    private int mPreviewRecipesPageNum;


    public static HomeRepository getInstance() {
        if (sInstance == null) {
            sInstance = new HomeRepository();
            return sInstance;
        }
        return sInstance;
    }

    private HomeRepository() {
        mHomeApiClient = HomeApiClient.getInstance();
    }

    public MutableLiveData<List<Recipe>> getPreviewRecipesLd() {
        return mHomeApiClient.getPreviewRecipesLd();
    }

    public void getPreviewRecipes(String query, int pageNum) {
        mPreviewRecipesQuery = query;
        mPreviewRecipesPageNum = pageNum;
        if (mPreviewRecipesPageNum == 0)
            mPreviewRecipesPageNum = 1;
        mHomeApiClient.getPreviewRecipesApi(mPreviewRecipesQuery, mPreviewRecipesPageNum);
    }

    public void getPreviewRecipesNextPage() {
        mHomeApiClient.getPreviewRecipesApi(mPreviewRecipesQuery, mPreviewRecipesPageNum++);
    }


    /**
     * @HardCoded should receive from server
     * TODO: MutableLiveData should change to Observable<>
     */
    public LiveData<List<Banner>> getSliderBannerList() {
        ArrayList<Banner> sliderBannerList = new ArrayList<>();
        MutableLiveData<List<Banner>> bannerMld = new MutableLiveData<>();
        sliderBannerList.add(Banner.create("0", "https://lmmks.com/wp-content/uploads/2019/05/php4CVHex.jpg", "slider0"));
        sliderBannerList.add(Banner.create("1", "https://media-cdn.tripadvisor.com/media/photo-s/15/07/39/73/fantasy-pepperoni-jalopeno.jpg", "slider1"));
        sliderBannerList.add(Banner.create("2", "https://eatwithme.net/wp-content/uploads/2020/02/Fast-Food-Restaurant-Sample-Business-Plan-1-min.jpg", "slider2"));
        sliderBannerList.add(Banner.create("3", "https://chicago.cbslocal.com/wp-content/uploads/sites/15116062/2020/03/hot-dog.jpg?w=900", "slider3"));
        sliderBannerList.add(Banner.create("4", "https://www.isitunhealthy.com/wp-content/uploads/2020/01/Fast-Food1.jpg", "slider4"));
        bannerMld.setValue(sliderBannerList);
        return bannerMld;
    }

    /**
     * @HardCoded should receive from server
     * TODO: MutableLiveData should change to Observable<>
     */
    public LiveData<List<Category>> getCategoryPreviewList() {
        List<Category> categoryPreviewList = new ArrayList<>();
        List<Category> abstractSubcategoryList = new ArrayList<>();
        MutableLiveData<List<Category>> categoryMld = new MutableLiveData<>();
        categoryPreviewList.add(Category.create("0", "غداهای ایرانی", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("1", "غذاهای سنتی", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("2", "فست فود", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("3", "نوشیدنی", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("4", "دسر و سالاد", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("5", "اسنک", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("6", "کافه", "", true, abstractSubcategoryList, null));
        categoryPreviewList.add(Category.create("7", "بستنی", "", true, abstractSubcategoryList, null));
        categoryMld.setValue(categoryPreviewList);
        return categoryMld;
    }

    /**
     * @HardCoded should receive from server
     * TODO: MutableLiveData should change to Observable<>
     */
    public LiveData<List<Product>> getProductPreviewList() {
        List<Product> productPreviewList = new ArrayList<>();
        List<Product> relatedProductList = new ArrayList<>();
        List<String> imageUrlList = new ArrayList<>();
        List<Category> parentCategoryList = new ArrayList<>();
        Specifications specifications = Specifications.create(new LinkedHashMap<>(), null, null, null);
        MutableLiveData<List<Product>> productMld = new MutableLiveData<>();
        String avatarUrl = "https://dkstatics-public.digikala.com/digikala-products/4297027.jpg?x-oss-process=image/resize,m_lfit,h_350,w_350/quality,q_60";

        /*imageUrlList.add("https://i.ibb.co/7nGSHRs/Screenshot-20191018-141540-Digikala.jpg");
        imageUrlList.add("https://i.ibb.co/7QnHzys/Screenshot-20191018-141626-Digikala.jpg");
        imageUrlList.add("https://i.ibb.co/Gcf0trn/Screenshot-20191018-141631-Digikala.jpg");
        imageUrlList.add("https://i.ibb.co/qn8PY7w/product-detail-slider1.jpg");*/

        relatedProductList.add(Product.create("0", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("1", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("2", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("3", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("4", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("5", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));
        relatedProductList.add(Product.create("6", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, null, 0, null));

        productPreviewList.add(Product.create("0", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("1", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("2", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("3", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("4", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("5", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("6", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("7", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("8", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));

        productPreviewList.add(Product.create("9", "فیله ساده مهیا پروتئین مقدار 0.9 کیلوگرم", "تازه و مقوی، سرشار از ویتامین", avatarUrl,
            "", "دیجی کالا", "دیجی کالا", 6, specifications, parentCategoryList, imageUrlList, 101000,
            15.0, 0, true, true, relatedProductList, 0, null));
        productMld.setValue(productPreviewList);
        return productMld;
    }


    public List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        List<Category> abstractSubcategroyList = new ArrayList<>();
        MutableLiveData<List<Category>> categoryMld = new MutableLiveData<>();

        abstractSubcategroyList.add(Category.create("0", "کالای دیجیتال", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("1", "آرایشی، بهداشتی و سلامت", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("2", "خودرو، ابزار و اداری", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("3", "خانه و آشپزخانه", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("4", "کتاب، لوازم تحریر و هنر", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("5", "اسباب بازی، کودک و نوزاد", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("6", "ورزش و سفر", null, true, abstractSubcategroyList, null));
        abstractSubcategroyList.add(Category.create("7", "خوردنی و آشامیدنی", null, true, abstractSubcategroyList, null));

        categoryList.add(Category.create("0", "کالای دیجیتال", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("1", "آرایشی، بهداشتی و سلامت", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("2", "خودرو، ابزار و اداری", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("3", "خانه و آشپزخانه", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("4", "کتاب، لوازم تحریر و هنر", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("5", "اسباب بازی، کودک و نوزاد", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("6", "ورزش و سفر", null, true, abstractSubcategroyList, null));
        categoryList.add(Category.create("7", "خوردنی و آشامیدنی", null, true, abstractSubcategroyList, null));
        categoryMld.postValue(categoryList);

        return categoryList;
    }

    /**
     * @HardCoded should recieve from server
     * MutableLiveData should change to Obeservable<>
     */
    public Product getProductById(String productId) {
        List<Product> products = getProductPreviewList().getValue();
        for (Product product : products) {
            if (product.id().equals(productId))
                return product;
        }
        return null;
    }

}
