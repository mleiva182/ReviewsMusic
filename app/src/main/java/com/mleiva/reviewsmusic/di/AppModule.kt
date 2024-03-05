package com.mleiva.reviewsmusic.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.mleiva.reviewsmusic.core.Constants.POSTS
import com.mleiva.reviewsmusic.core.Constants.USERS
import com.mleiva.reviewsmusic.data.repository.AuthRepositoryImpl
import com.mleiva.reviewsmusic.data.repository.PostsRepositoryImpl
import com.mleiva.reviewsmusic.data.repository.UsersRepositoryImpl
import com.mleiva.reviewsmusic.domain.repository.AuthRepository
import com.mleiva.reviewsmusic.domain.repository.PostsRepository
import com.mleiva.reviewsmusic.domain.repository.UsersRepository
import com.mleiva.reviewsmusic.domain.use_cases.auth.AuthUseCase
import com.mleiva.reviewsmusic.domain.use_cases.auth.GetCurrentUser
import com.mleiva.reviewsmusic.domain.use_cases.auth.LogOut
import com.mleiva.reviewsmusic.domain.use_cases.auth.Login
import com.mleiva.reviewsmusic.domain.use_cases.auth.SignUp
import com.mleiva.reviewsmusic.domain.use_cases.posts.CreatePosts
import com.mleiva.reviewsmusic.domain.use_cases.posts.DeletePost
import com.mleiva.reviewsmusic.domain.use_cases.posts.GetPostsByIdUser
import com.mleiva.reviewsmusic.domain.use_cases.posts.PostsUseCases
import com.mleiva.reviewsmusic.domain.use_cases.users.Create
import com.mleiva.reviewsmusic.domain.use_cases.users.GetUserById
import com.mleiva.reviewsmusic.domain.use_cases.users.SaveImage
import com.mleiva.reviewsmusic.domain.use_cases.users.Update
import com.mleiva.reviewsmusic.domain.use_cases.users.UsersUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

/***
 * Project: ReviewsMusic
 * From: com.mleiva.reviewsmusic.di
 * Creted by: Marcelo Leiva on 26-02-2024 at 20:35
 ***/
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Named(USERS)
    fun provideUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImpl): UsersRepository = impl

    @Provides
    fun providePostsRepository(impl: PostsRepositoryImpl): PostsRepository = impl

    @Provides
    fun provideAuthUseCases(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        signUp = SignUp(repository),
        logout = LogOut(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        saveImage = SaveImage(repository),
        update = Update(repository)
    )

    @Provides
    fun providesPostsUseCases(repository: PostsRepository) = PostsUseCases(
        create = CreatePosts(repository),
        getPostsByIdUser = GetPostsByIdUser(repository),
        deletePost = DeletePost(repository)
    )

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(POSTS)
    fun providePostsRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    @Named(POSTS)
    fun ProvidesStoragePostsRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POSTS)


}